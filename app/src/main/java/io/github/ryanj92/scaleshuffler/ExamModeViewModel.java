package io.github.ryanj92.scaleshuffler;

import androidx.lifecycle.ViewModel;

public class ExamModeViewModel extends ViewModel {

    public String currentElementName;
    public String currentElementKey;
    public String currentElementHands;
    public String currentElementTempo;
    public int currentElementNumber;

    public void putElementName(String elementName) { this.currentElementName = elementName; }

    public void putElementKey(String elementKey) { this.currentElementKey = elementKey; }

    public void putElementHands(String elementHands) { this.currentElementHands = elementHands; }

    public void putElementTempo(String elementTempo) { this.currentElementTempo = elementTempo; }

    public void putElementNumber(int elementNumber) {this.currentElementNumber = elementNumber; }

    public String getElementName() { return currentElementName; }

    public String getElementKey() { return currentElementKey; }

    public String getElementHands() { return currentElementHands; }

    public String getElementTempo() { return currentElementTempo; }

    public int getElementNumber() { return currentElementNumber; }

}
