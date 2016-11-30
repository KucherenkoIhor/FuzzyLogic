package udhtu.fuzzy.numbers;

public abstract class FunctionalNumber implements Functional {

    public final double minValue;
    public final double maxValue;

    protected String name = "chart";

    protected FunctionalNumber(double minValue, double maxValue) {
        if(minValue >= maxValue) throw new IllegalArgumentException();
        if(maxValue <= minValue) throw new IllegalArgumentException();
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double function(double x);

    /*

    public void drawChart(){
        Chart.draw(getDataSet(), name);
    }

    public XYSeriesCollection getDataSet() {
        final XYSeries series = new XYSeries(name);
        for(int step = minValue; step <= maxValue; step++) {
            series.add(step, function(step));
        }
        final XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(series);
        return dataSet;
    }*/

}