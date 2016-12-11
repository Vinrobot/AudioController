package ch.vinrobot.audio.exception;

import javax.sound.sampled.Line;

public class AudioException extends Exception {

	private static final long serialVersionUID = -2958005597867869469L;

	public AudioException(String err) {
        super(err);
    }

    public AudioException(Line line) {
        this("Line is closed" + ((line != null) ? ": " + line.getLineInfo() + " (" + line.getClass().getSimpleName() + ")" : ""));
    }
}
