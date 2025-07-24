import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class player {
		private String name;
		private int balance;
		private StandardCard[] pockCards = new StandardCard[2];
		private boolean isInGame;
	public void Player(String name, int balance, StandardCard[] pockCards)	{
		this.name = name;
		this.balance = balance;
		this.pockCards = pockCards;
		this.isInGame = true;
	}
	public String getName() {
		return this.name;
	}
	public int getBalance() {
		return this.balance;
	}
	public void addToBalance(int additionAmmount) {
		this.balance+=additionAmmount;
	}
	public void subtractToBalance(int reduceAmmount) {
		this.balance-=reduceAmmount;
	}
	public StandardCard[] getPockCards() {
		return this.pockCards;
	}
	public boolean isInGame() {
		return this.isInGame;
	}
	public void setIsInGame(boolean isInGame) {
		this.isInGame =isInGame;
	}
	
	public String Status() {
		return ("Player " + this.name + "has a balance of " + this.balance + "and has pocket cards " + this.pockCards[0]+ "and " +this.pockCards[1] + "In game:" + this.isInGame);
	}
	
	public static void main(String[] args) {
		
	}
	public void resetHand() {
        hasCards = false;
        hand.removeAllCards();
        resetBet();
    }
	 public void resetBet() {
        bet = 0;
        action = (hasCards() && cash == 0) ? Action.ALL_IN : null;
    }
	 public void setBet(int bet) {
        this.bet = bet;
    }
	 public boolean isAllIn() {
        return hasCards() && (cash == 0);
    }
	  public void payCash(int amount) {
        if (amount > cash) {
            throw new IllegalStateException("Player asked to pay more cash than he owns!");
        }
        cash -= amount;
    }
	

}
