package ch.vinrobot.audio;

import java.util.HashSet;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import ch.vinrobot.audio.exception.AudioException;

public class Audio {

	public static AudioController getMasterController() throws AudioException {
		return new AudioController(getOutputLine("Master", "Speaker"));
	}

	public static AudioController getHeadsetController() throws AudioException {
		return new AudioController(getOutputLine("Headphone"));
	}

	public static Line getOutputLine(String... names) {
		if (names != null && names.length > 0) {
			for (Mixer mxr : getMixers()) {
				for (Line line : getOutputLines(mxr)) {
					String nfo = line.getLineInfo().toString().toUpperCase();
					for (String name : names) {
						if (name != null && nfo.contains(name.toUpperCase())) {
							return line;
						}
					}
				}
			}
		}
		return null;
	}

	public static HashSet<Mixer> getMixers() {
		HashSet<Mixer> mixers = new HashSet<>();
		Mixer.Info[] infos = AudioSystem.getMixerInfo();
		if (infos != null && infos.length > 0) {
			for (Mixer.Info info : infos) {
				Mixer mxr = AudioSystem.getMixer(info);
				if (mxr != null) {
					mixers.add(mxr);
				}
			}
		}
		return mixers;
	}

	public static HashSet<Line> getOutputLines(Mixer mxr) {
		return getLines(mxr, mxr.getTargetLineInfo());
	}

	private static HashSet<Line> getLines(Mixer mxr, Line.Info[] tli) {
		HashSet<Line> lines = new HashSet<>();
		if (mxr != null && tli != null && tli.length > 0) {
			for (Line.Info li : tli) {
				try {
					Line line = mxr.getLine(li);
					if (line != null) {
						lines.add(line);
					}
				} catch (LineUnavailableException ex) {
				}
			}
		}
		return lines;
	}
}
