package ch.vinrobot.audio;

import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;

import ch.vinrobot.audio.exception.AudioException;
import ch.vinrobot.audio.exception.RuntimeAudioException;

public class AudioController implements IAudioController {
    
    private final Line line;

    public AudioController(Line ln) throws AudioException {
        if (ln == null)
			throw new AudioException("Output port not found");
        this.line = ln;
    }

    @Override
    public AudioController setVolume(float volume) {
        if (volume < 0 || volume > 1)
			throw new RuntimeAudioException("Volume", 0, 1);
        if (this.open()) {
            try {
                FloatControl control = this.getVolumeControl();
                if (control != null)
					control.setValue(volume);
            } catch (AudioException ex) {
            } finally {
                this.close();
            }
        }
        return this;
    }

    @Override
    public float getVolume() {
        float volume = -1;
        if (this.open()) {
            try {
                FloatControl control = this.getVolumeControl();
                if (control != null)
					volume = control.getValue();
            } catch (AudioException ex) {
            } finally {
                this.close();
            }
        }
        return volume;
    }

    @Override
    public AudioController setMuted(boolean mute) {
        if (this.open()) {
            try {
                BooleanControl control = this.getMuteControl();
                if (control != null)
					control.setValue(mute);
            } catch (AudioException ex) {
            } finally {
                this.close();
            }
        }
        return this;
    }

    @Override
    public boolean isMuted() {
        boolean muted = true;
        if (this.open()) {
            try {
                BooleanControl control = this.getMuteControl();
                if (control != null)
					muted = control.getValue();
            } catch (AudioException ex) {
            } finally {
                this.close();
            }
        }
        return muted;
    }

    @Override
    public boolean close() {
        boolean open = this.isOpen();
        if (open)
			this.line.close();
        return open;
    }

    @Override
    public boolean open() {
        boolean open = false;
        if (!this.isOpen()) {
            try {
                this.line.open();
                open = true;
            } catch (LineUnavailableException ex) {
            }
        }
        return open;
    }

    @Override
    public boolean isOpen() {
        return this.line.isOpen();
    }
    
    private FloatControl getVolumeControl() throws AudioException {
        return (FloatControl) this.getControl(FloatControl.Type.VOLUME);
    }

    private BooleanControl getMuteControl() throws AudioException {
        return (BooleanControl) this.getControl(BooleanControl.Type.MUTE);
    }

    private Control getControl(Control.Type type) throws AudioException {
        if (!this.isOpen())
			throw new AudioException(this.line);
        return this.findControl(type, this.line.getControls());
    }

    private Control findControl(Control.Type type, Control... ctrls) {
        Control rc = null;
        if (ctrls != null && ctrls.length > 0) {
            for (Control ctrl : ctrls) {
                if (ctrl.getType().equals(type)) {
                    rc = ctrl;
                    break;
                } else if (ctrl instanceof CompoundControl) {
                    Control cm = this.findControl(type, ((CompoundControl) ctrl).getMemberControls());
                    if (cm != null) {
                        rc = cm;
                        break;
                    }
                }
            }
        }
        return rc;
    }
}
