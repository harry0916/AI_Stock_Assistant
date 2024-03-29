package com.nus.stock.gatrader.dt;

import com.nus.stock.gatrader.biz.Candle;
import com.nus.stock.gatrader.biz.Decision;
import com.nus.stock.gatrader.biz.Indicator;


public class IndicatorNode extends Node {
	private static final long serialVersionUID = 4388207576901236554L;
	private Indicator indicator;
    private boolean value;
    
    public IndicatorNode(Indicator indicator, boolean value, Node left, Node right) {
        super(left,right);
        this.indicator = indicator;
        this.value = value;
    }
    
    public static IndicatorNode getRandomNode(){
        Indicator randomIndicator = Indicator.getRandomIndicator();
        boolean randomValue = Math.random() >= 0.5;
        DecisionNode leftNode = DecisionNode.getRandomNode();
        DecisionNode rightNode;
        if (leftNode.decision == Decision.BUY)
        	rightNode = new DecisionNode(Decision.SELL);
        else
            rightNode = new DecisionNode(Decision.BUY);
        return new IndicatorNode(randomIndicator, randomValue, leftNode, rightNode);
    }
    
    public void mutateIndicator(){
        this.indicator = Indicator.getRandomIndicator();
        this.value = Math.random() >= 0.5;
    }
    
    public void mutateIndicatorValue(){
        this.value = !this.value;
    }
    
    @Override
    public Decision evaluate(Candle c){
        if (c.indicatorList[indicator.getIndex()] == value)
            return right.evaluate(c);
        else
            return left.evaluate(c);
    }
    
    @Override
    public boolean isLeaf() {
        return false;
    }
    
    @Override
    public IndicatorNode clone(){
        return new IndicatorNode(this.indicator, this.value, this.left.clone(), this.right.clone());
    }
    

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

}
