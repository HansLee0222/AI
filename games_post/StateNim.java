public class StateNim extends State {
public int coin;


    public StateNim() {

      this.coin = 13;
      this.player = 1;
    }

    public StateNim(StateNim state) {

      this.coin = state.coin;
      this.player = state.player;
    }

    public String toString() {

    return this.coin + " coins left";
    }
}
