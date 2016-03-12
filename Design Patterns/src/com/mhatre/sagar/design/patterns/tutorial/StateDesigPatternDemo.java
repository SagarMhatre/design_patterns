package com.mhatre.sagar.design.patterns.tutorial;

//Ref : https://dzone.com/articles/design-patterns-state

public class StateDesigPatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
interface State {
	public void pressPlay(MP3PlayerContext context);
}

class StandbyState implements State {
	public void pressPlay(MP3PlayerContext context) {
		context.setState(new PlayingState());
	}
}

class PlayingState implements State {
	public void pressPlay(MP3PlayerContext context) {
		context.setState(new StandbyState());
	}
}
class MP3PlayerContext {
	private State state;

	private MP3PlayerContext(State state) {
		this.state = state;
	}

	public void play() {
		state.pressPlay(this);
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}
}