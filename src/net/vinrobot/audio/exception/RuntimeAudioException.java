package net.vinrobot.audio.exception;

public class RuntimeAudioException extends RuntimeException {

	private static final long serialVersionUID = 46790040450323214L;

	public RuntimeAudioException(String err) {
		super(err);
	}

	public RuntimeAudioException(String name, int min, int max) {
		this(name + " can only be set to a value from " + min + " to " + max + ".");
	}
}
