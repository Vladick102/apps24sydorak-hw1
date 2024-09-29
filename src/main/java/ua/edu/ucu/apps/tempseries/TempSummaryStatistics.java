package ua.edu.ucu.apps.tempseries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;
}
