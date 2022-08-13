package PriorityQueue;
/*
You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

        You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

        If the CPU is idle and there are no available tasks to process, the CPU remains idle.
        If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
        Once a task is started, the CPU will process the entire task without stopping.
        The CPU can finish a task then start a new one instantly.
        Return the order in which the CPU will process the tasks.



        Example 1:

        Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
        Output: [0,2,3,1]
        Explanation: The events go as follows:
        - At time = 1, task 0 is available to process. Available tasks = {0}.
        - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
        - At time = 2, task 1 is available to process. Available tasks = {1}.
        - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
        - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
        - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
        - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
        - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
        - At time = 10, the CPU finishes task 1 and becomes idle.
        Example 2:

        Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
        Output: [4,3,2,0,1]
        Explanation: The events go as follows:
        - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
        - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
        - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
        - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
        - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
        - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
        - At time = 40, the CPU finishes task 1 and becomes idle.


        Approach 1: Sorting + Min-Heap
        Intuition

        The CPU can only pick a task for execution after it is enqueued. Thus, we need to keep track of current time to see which tasks are available for the CPU and sort the tasks in increasing order of their enqueue time.

        Now, we can create a list of tasks available at the current time (tasks whose enqueue time is less than or equal to the current time). From this list, we will select the task with the shortest processing time, so we can think of sorting all the available tasks in increasing order of their processing time.
        Then after selecting a task, the CPU will run that task until it is complete, and the current time will increase by the processing time of the selected task.
        After increasing current time, some more tasks might become available for execution. We would then add these tasks to our list and again sort the list in increasing order of processing time. This approach will work, but sorting our available task list every time we update it will be costly in terms of runtime.

        Thus, this gives us a hint of using a min-heap data structure. If you are new to the heap data structure, we recommend you visit our Heap Explore Card.
        A min-heap is a tree-like data structure that always stores the minimum valued element at the top using some comparison (processing time here, or task index in case of a tie) and where insertion and removal of elements (tasks) take logarithmic time.
        Hence, using min-heap will relieve us from the repeated sorting of our list since we can insert new tasks and retrieve the shortest task from the heap in logarithmic time.

        Hence, the flow of our approach is something like:
        (a) We will insert all the currently available tasks in the min-heap.
        (b) Pick the task with the shortest processing time.
        (c) Increase the current time by the processing time of the selected task.

        Now, one point to note here is that let's say current time is 0, the heap is empty, and the next available task will enqueue at 10. The CPU will sit idle until current time reaches 10. Instead of incrementing current time by 1, we will update current time directly to 10, which will reduce the number of iterations in our approach and improve the run-time.

        This approach can be better understood with the following slideshow:

        Current
        1 / 27

        Algorithm

        Initialize some data-structures:

        nextTask, min-heap to store task with minimum processing time on the top.
        sortedTasks, array to store tasks in sorted order on the basis of their enqueue time.
        tasksProcessingOrder, array to store the order in which the CPU will process the tasks.
        Add all of the tasks (with their index) to sortedTasks and sort the array using the built-in sort function.

        Initialize currTime to 0.

        While there are tasks in the sortedTasks array that have not been added to the min-heap, or there are tasks remaining in the min-heap:

        Check if the min-heap is empty and if the enqueue time of the next task is greater than currTime. If so, then update the currTime to the next task's enqueue time.
        Insert all the available tasks (tasks whose enqueue time is less than or equal to currTime), into the min-heap.
        Pick the task on the top of the min-heap, increment currTime by its processing time, and add its index to the tasksProcessingOrder array.
        Return the tasksProcessingOrder array.
        */
 import java.util.*;
public class SingleThreadedCPU {
        public int[] getOrder(int[][] tasks) {

            // Sort based on min task processing time or min task index.
            // Store enqueue time, processing time, task index.
            PriorityQueue<int[]> nextTask = new PriorityQueue<int[]>((a, b) -> (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));

            // Store task enqueue time, processing time, index.
            int sortedTasks[][] = new int[tasks.length][3];
            for (int i = 0; i < tasks.length; ++i) {
                sortedTasks[i][0] = tasks[i][0];
                sortedTasks[i][1] = tasks[i][1];
                sortedTasks[i][2] = i;
            }

            Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));
            int tasksProcessingOrder[] = new int[tasks.length];

            long currTime = 0;
            int taskIndex = 0;
            int ansIndex = 0;

            // Stop when no tasks are left in array and heap.
            while (taskIndex < tasks.length || !nextTask.isEmpty()) {
                if (nextTask.isEmpty() && currTime < sortedTasks[taskIndex][0]) {
                    // When the heap is empty, try updating currTime to next task's enqueue time.
                    currTime = sortedTasks[taskIndex][0];
                }

                // Push all the tasks whose enqueueTime <= currtTime into the heap.
                while (taskIndex < tasks.length && currTime >= sortedTasks[taskIndex][0]) {
                    nextTask.add(sortedTasks[taskIndex]);
                    ++taskIndex;
                }

                int processTime = nextTask.peek()[1];
                int index = nextTask.peek()[2];
                nextTask.remove();

                // Complete this task and increment currTime.
                currTime += processTime;
                tasksProcessingOrder[ansIndex++] = index;
            }

            return tasksProcessingOrder;
        }

}
