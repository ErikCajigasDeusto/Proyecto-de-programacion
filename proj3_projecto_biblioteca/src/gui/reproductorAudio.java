package gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class reproductorAudio {
	//Variables
	private String mus;
	private String sfx;
	private AudioInputStream audioInputStreamMus;
	private AudioInputStream audioInputStreamSFX;
	private Clip clipMus;
	private Clip clipSFX;
	
	//Metodos
	private void pause() {
		clipMus.stop();
	}
	public void playMusAlquilar() {
		pause();
		mus = "";
		try {
			audioInputStreamMus = AudioSystem.getAudioInputStream(new File(mus).getAbsoluteFile());
		}catch(IOException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		}
		try {
			clipMus = AudioSystem.getClip();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clipMus.open(audioInputStreamMus);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		clipMus.start();
		clipMus.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
