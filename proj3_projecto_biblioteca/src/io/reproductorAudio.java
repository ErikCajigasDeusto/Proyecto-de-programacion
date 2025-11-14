package io;

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
	private long clipMusTime;
	private long clipSFXMaxTime;
	
	//Pause
	private void pause() {
		clipMusTime = clipMus.getMicrosecondPosition();
		clipMus.stop();
	}
	//Unpause
	private void unPause() {
		if(clipSFXMaxTime>clipSFX.getMicrosecondPosition()) {
			unPause();
		}else {
			clipMus.setMicrosecondPosition(clipMusTime);
			clipMus.start();
		}
	}
	//Musica de ventana Alquilar
	public void playMusAlquilar() {
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
	//Musica de ventana Devolver
	public void playMusDevolver() {
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
	
	//Musica de ventanas Usuario
	public void playMusUsuario() {
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
	//Musica de ventana Principal
	public void playMusTitleTheme() {
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
	//sfx correcto
	public void playCorrecto() {
		pause();
		sfx = "";
		try {
			audioInputStreamSFX = AudioSystem.getAudioInputStream(new File(sfx).getAbsoluteFile());
		}catch(IOException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		}
		try {
			clipSFX = AudioSystem.getClip();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clipSFX.open(audioInputStreamSFX);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		clipSFX.start();
		clipSFXMaxTime = clipSFX.getMicrosecondLength();
		unPause();
	}
	//sfx pagar
	public void playPagar() {
		pause();
		sfx = "";
		try {
			audioInputStreamSFX = AudioSystem.getAudioInputStream(new File(sfx).getAbsoluteFile());
		}catch(IOException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		}
		try {
			clipSFX = AudioSystem.getClip();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clipSFX.open(audioInputStreamSFX);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		clipSFX.start();
		clipSFXMaxTime = clipSFX.getMicrosecondLength();
		unPause();
	}
	//sfx incorrecto
	public void playIncorrecto() {
		pause();
		sfx = "";
		try {
			audioInputStreamSFX = AudioSystem.getAudioInputStream(new File(sfx).getAbsoluteFile());
		}catch(IOException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		}
		try {
			clipSFX = AudioSystem.getClip();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clipSFX.open(audioInputStreamSFX);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		clipSFX.start();
		clipSFXMaxTime = clipSFX.getMicrosecondLength();
		unPause();
	}
}
