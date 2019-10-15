public final class TempSummaryStatistics {
    final double avgTemp;
    final double devTemp;
    final double minTemp;
    final double maxTemp;
    public TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp){
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
    public String toString(){
        return "   ===Statistics===   " + "\n" +
                "Average Temperature: " + avgTemp +  "\n" +
                "Standard Deviation: " + devTemp + "\n" +
                "Minimum Temperature: " + minTemp + "\n" +
                "Maximum Temperature: " + maxTemp + "\n";
    }
}
