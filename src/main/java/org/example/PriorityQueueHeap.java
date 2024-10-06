/**This is the implementation of a Priority Que, but as a heap*/
package org.example;

public class PriorityQueueHeap implements PriorityQueue{
    /**Private members of the class*/
    private int length;
    private House[] heapArr;
    /**Default Constructor*/
    public PriorityQueueHeap(){
        this.length = 0;
        this.heapArr = new House[10];

    }
    /**Copy Constructor*/
    public PriorityQueueHeap(PriorityQueueHeap other){
        this.length = other.length;
        this.heapArr = new House[other.heapArr.length];
        for (int i = 0; i < other.length; i++) {
            this.heapArr[i] = other.heapArr[i].deepCopy();
        }
    }
    /**Deep copy method that returns a heap*/
    public PriorityQueueHeap deepCopy(){
        PriorityQueueHeap ans = new PriorityQueueHeap(this);
        return ans;
    }
    /**Helper method that deletes the Root of the array/heap*/
    private void Delete(){
        heapArr[0] = heapArr[length-1];
        heapArr[length-1]=null;
        length--;
        heapifyDown();

    }
    /**Helper method that swaps two houses in a heap*/
    private void swap(int index, int index2){
    House tempValue = heapArr[index];
    heapArr[index]=heapArr[index2];
    heapArr[index2] = tempValue;
    }
    /**Helper method that does heapify up. It brings the item at the given index to the correct position by going up*/
    private void heapifyUp(int index){
        while(index>0&&heapArr[index].getValue()>heapArr[getParentIndex(index)].getValue()){
            swap(index,getParentIndex(index));
            index = getParentIndex(index);

        }


    }
    /**Helper method that does Heapify down. It brings the item at the given index to the correct position by going down*/
    private void heapifyDown(){
        int tempIndex = 0;
        int childIndex = getGreatestChildIndex(tempIndex);
        while(childIndex != -1 && heapArr[tempIndex].getValue()<heapArr[childIndex].getValue()){
            swap(tempIndex,childIndex);
            tempIndex = childIndex;
            childIndex = getGreatestChildIndex(tempIndex);
        }

    }
    /**Helper method that doubles the size of the array if it runs out of space.*/
    private void Resize(){
        House[] tempArr = new House[heapArr.length*2];
        for (int i = 0; i < heapArr.length; i++) {
            tempArr[i] = heapArr[i];
        }
        heapArr = tempArr;
    }
    /**Method that adds an item to the heap*/
    @Override
    public void add(House a) {
        if(length == heapArr.length){
            Resize();
        }
        heapArr[length] = a;
        heapifyUp(length);
        length++;
    }
    /**Method that gets the most expensive item from the heap and then deletes it. It returns the most expensive House*/
    @Override
    public House getMostExpensive() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }
        House house = heapArr[0];
        Delete();
        return house;
    }
    /**Method that clears the heap*/
    @Override
    public void clear() {
    length = 0;
    }
    /**Method that returns the length of the heap*/
    @Override
    public int getLength() {
        return length;
    }
    /**Method that returns a boolean if the heap is empty or not*/
    @Override
    public boolean isEmpty() {
        return length == 0;
    }
    /**Helper method to get the parent Index*/
    private int getParentIndex(int index){
        return (index-1)/2;
    }
    /**Helper method to return the left child index*/
    private int getLeftChildIndex(int index){
        return 2 * index +1;
    }
    /**Helper method to get the index of the right child.*/
    private int getRightChildIndex(int index){
        return 2*index+2;
    }
    /**Helper method to get the index of the house will the greater value*/
    private int getGreatestChildIndex(int index) {
        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);

        boolean hasLeftChild = leftIndex < length;   // Check if left child exists
        boolean hasRightChild = rightIndex < length; // Check if right child exists

        // If both children exist, find the larger of the two
        if (hasLeftChild && hasRightChild) {
            if (heapArr[leftIndex].getValue() > heapArr[index].getValue() &&
                    heapArr[rightIndex].getValue() > heapArr[index].getValue()) {
                return heapArr[leftIndex].getValue() > heapArr[rightIndex].getValue() ? leftIndex : rightIndex;
            } else if (heapArr[leftIndex].getValue() > heapArr[index].getValue()) {
                return leftIndex;
            } else if (heapArr[rightIndex].getValue() > heapArr[index].getValue()) {
                return rightIndex;
            }
        } else if (hasLeftChild && heapArr[leftIndex].getValue() > heapArr[index].getValue()) {
            return leftIndex;
        }

        return -1; // No valid children to swap with
    }
    /**Method that helps to print out items in the heap*/
    public void print(){
        for (int i = 0; i < length; i++) {
            System.out.println(i+ " :"+ heapArr[i].getOwner()+ ": " + heapArr[i].getValue());

        }
    }
}
