package Controller;

public interface ISimulationObserver {
    void onStatsChanged(int rabbitCount, int carrotCount, int maxGen);
}
