package Matrix;
/*A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

        The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.

        Your task is to move the box 'B' to the target position 'T' under the following rules:

        The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
        The character '.' represents the floor which means a free cell to walk.
        The character '#' represents the wall which means an obstacle (impossible to walk there).
        There is only one box 'B' and one target cell 'T' in the grid.
        The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
        The player cannot walk through the box.
        Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.



        Example 1:


        Input: grid = [["#","#","#","#","#","#"],
        ["#","T","#","#","#","#"],
        ["#",".",".","B",".","#"],
        ["#",".","#","#",".","#"],
        ["#",".",".",".","S","#"],
        ["#","#","#","#","#","#"]]
        Output: 3
        Explanation: We return only the number of times the box is pushed.
        Example 2:

        Input: grid = [["#","#","#","#","#","#"],
        ["#","T","#","#","#","#"],
        ["#",".",".","B",".","#"],
        ["#","#","#","#",".","#"],
        ["#",".",".",".","S","#"],
        ["#","#","#","#","#","#"]]
        Output: -1
        Example 3:

        Input: grid = [["#","#","#","#","#","#"],
        ["#","T",".",".","#","#"],
        ["#",".","#","B",".","#"],
        ["#",".",".",".",".","#"],
        ["#",".",".",".","S","#"],
        ["#","#","#","#","#","#"]]
        Output: 5
        Explanation: push the box down, left, left, up and up. */
public class MinimumMovestoMoveaBox {
    MinimumMovestoMoveaBox() {}
}
   /*
   Heuristic (an under-estimate of the remaining moves required) is the Manhattan distance between box and target.
    A state consist of box and person locations together.

    Repeatedly pop the state with the lowest heuristic + previous moves off the heap.
    Attempt to move the person in all 4 directions.
    If any direction moves the person to the box, check if the box can move to the nex position in the grid.

// use A-SEARCH ALGORITHM
            rows, cols = len(grid), len(grid[0])
            for r in range(rows):
            for c in range(cols):
            if grid[r][c] == "T":
    target = (r, c)
            if grid[r][c] == "B":
    start_box = (r, c)
            if grid[r][c] == "S":
    start_person = (r, c)

    def heuristic(box):
            return abs(target[0] - box[0]) + abs(target[1] - box[1])

    def out_bounds(location):  # return whether the location is in the grid and not a wall
            r, c = location
            if r < 0 or r >= rows:
            return True
            if c < 0 or c >= cols:
            return True
            return grid[r][c] == "#"

    heap = [[heuristic(start_box), 0, start_person, start_box]]
    visited = set()

        while heap:
    _, moves, person, box = heapq.heappop(heap)
            if box == target:
            return moves
            if (person, box) in visited: # do not visit same state again
                continue
                        visited.add((person, box))

            for dr, dc in [[0, 1], [1, 0], [-1, 0], [0, -1]]:
    new_person = (person[0] + dr, person[1] + dc)
            if out_bounds(new_person):
            continue

            if new_person == box:
    new_box = (box[0] + dr, box[1] + dc)
            if out_bounds(new_box):
            continue
            heapq.heappush(heap, [heuristic(new_box) + moves + 1, moves + 1, new_person, new_box])
            else:
            heapq.heappush(heap, [heuristic(box) + moves, moves, new_person, box]) # box remains same

        return -1

             */