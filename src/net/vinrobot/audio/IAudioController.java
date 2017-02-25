package net.vinrobot.audio;

/**
 * @author vincent
 */
public interface IAudioController {

	public IAudioController setVolume(float volume);

	public float getVolume();

	public IAudioController setMuted(boolean mute);

	public boolean isMuted();

	public boolean open();

	public boolean isOpen();

	public boolean close();

}
