/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardflipping;

/**
 * This class is Player Class for Card Flipping.
 * 
 * @author Jungwon Lim
 */
public class Player {

    // *** declare instant variables
    /** start time user started play **/
    private double startTime = 0;
    /** current time **/
    private double lapTime;
    /** end time user finished play **/
    private double endTime;
    /** fastest record user recorded **/
    private double bestRecord;
    /** latest record user recorded **/
    private double currentRecord;
    /** either it is the first attempt play **/
    private boolean isFirstPlay = true;
    /** either the latest record is a new best record **/
    private boolean isNewBestRecord = false;

    /**
     * start the game, mark the start time
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * finish the game, mark the end time
     */
    public void finish() {
        endTime = System.currentTimeMillis();
        currentRecord = endTime - startTime;
        if (!isFirstPlay) {
            if (currentRecord < bestRecord) {
                bestRecord = currentRecord;
                isNewBestRecord = true;
            } else {
                isNewBestRecord = false;
            }
        } else {
            bestRecord = currentRecord;
        }
        isFirstPlay = false;
        startTime = 0;

    }

    /**
     * mark the current time
     */
    public void lap() {
        lapTime = System.currentTimeMillis();
    }

    /**
     * get the start time
     * @return start time of the current game
     */
    public double getStartTime() {
        return startTime;
    }
    
    /**
     * get the current passed time
     *
     * @return time passed since start until now
     */
    public double getElapsedRecord() {
        lap();
        return lapTime - startTime;
    }

    /**
     * get the latest record time passed to finish the game
     * @return currentRecord - the latest record time passed to finish the game
     */
    public double getFinishRecord() {
        return currentRecord;
    }

    /**
     * get the best record of all time
     * @return bestRecord - the best record of all time
     */
    public double getBestRecord() {
        return bestRecord;
    }

    /**
     * get if the latest record is the new best record
     * @return true if the latest record is the new best record, false if not
     */
    public boolean getIsNewBestRecord() {
        return isNewBestRecord;
    }
    
    /**
     * show all the instants of Player as a string
     * @return all instants as a string
     */
    @Override
    public String toString() {
        return "\nstartTime: " + startTime + "\nlapTime: " + lapTime
                + "\nendTime: " + endTime + "\nbestRecord: " + bestRecord
                + "\ncurrentRecord: " + currentRecord + "\nisFirstPlay: "
                + isFirstPlay + "\n";
    }

}
