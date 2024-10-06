/**This is an interface that gives methods that are needed for a priority queue*/

package org.example;

public interface PriorityQueue {
    /**Method for adding a item to a priority que*/
    public void add(House a);
    /**Method to get the most expensive from a priority que and delete it after*/
    public House getMostExpensive();
    /**Method for clearing the priority que*/
    public void clear();
    /**Method for getting the total amount of items from the priority que*/
    public int getLength();
    /**Method to check if the priority que is empty*/
    public boolean isEmpty();

}
