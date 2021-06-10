package logic;

import java.util.Objects;

public class Ratio {

    private Integer firstNumber;
    private Integer secondNumber;
    private Double resultOfRatio;


    public Ratio() {
    }
    public void Ratio2() {
    }


    public Ratio(Integer firstNumber, Integer secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.resultOfRatio = (double)(firstNumber/secondNumber)*100;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Double getResultOfRatio() {
        return resultOfRatio;
    }

    public void setResultOfRatio(Double resultOfRatio) {
        this.resultOfRatio = resultOfRatio;
    }

    @Override
    public String toString() {
        return "Ratio{" +
                "firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                ", resultOfRatio=" + resultOfRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratio ratio = (Ratio) o;
        return Objects.equals(firstNumber, ratio.firstNumber) && Objects.equals(secondNumber, ratio.secondNumber) && Objects.equals(resultOfRatio, ratio.resultOfRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumber, secondNumber, resultOfRatio);
    }
}