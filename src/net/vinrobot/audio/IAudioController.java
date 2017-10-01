package net.vinrobot.audio;

import net.vinrobot.audio.exception.AudioException;

/**
 * @author vincent
 */
public interface IAudioController {

	public IAudioController setVolume(float volume) throws AudioException;

	public float getVolume() throws AudioException;

	public IAudioController setMuted(boolean mute) throws AudioException;

	public boolean isMuted() throws AudioException;

	public boolean open();

	public boolean isOpen();

	public boolean close();

}
