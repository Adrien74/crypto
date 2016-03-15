package org.rumeur.main;

public class Rumeur {
	private SocialNode launcher;
	private SocialNode target;
	private String rumeurColor;

	public Rumeur(SocialNode launcher, SocialNode target, String rumeurColor) {
		super();
		this.launcher = launcher;
		this.target = target;
		this.rumeurColor = rumeurColor;
	}

	public SocialNode getTarget() {
		return target;
	}

	public void setTarget(SocialNode target) {
		this.target = target;
	}

	public String getRumeurColor() {
		return rumeurColor;
	}

	public void setRumeurColor(String rumeurColor) {
		this.rumeurColor = rumeurColor;
	}

	public SocialNode getLauncher() {
		return launcher;
	}

	public void setLauncher(SocialNode launcher) {
		this.launcher = launcher;
	}
}
