//this doesnt work because the numbers get too large, but I wanted to try a path finding solution

public class Solution
{
    public int MaxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts)
    {
        var largest = 0;
        var matrix = new int[w, h];
        for (var x = 0; x < matrix.GetLength(0); x++)
        {
            for (var y = 0; y < matrix.GetLength(1); y++)
            {
                if (matrix[x, y] != 0) continue;
                var result = 1; 
                Move(x, y, horizontalCuts, verticalCuts, matrix, ref result);
                if (result > largest) { 
                    largest = result;
                }
            }
        }
        return largest;
    }

    public void Move(int x, int y, int[] hCuts, int[] vCuts, int[,] matrix, ref int count) {
        matrix[x, y] = count;
        if (TryMoveInDirection(x, y, 0, 1, hCuts, vCuts, matrix, ref count)) return;
        if (TryMoveInDirection(x, y, 0, -1, hCuts, vCuts, matrix, ref count)) return;
        if (TryMoveInDirection(x, y, 1, 0, hCuts, vCuts, matrix, ref count)) return;
        if (TryMoveInDirection(x, y, -1, 0, hCuts, vCuts, matrix, ref count)) return;
    }

    public bool TryMoveInDirection(int x, int y, int directionX, int directionY, int[] hCuts, int[] vCuts, int[,] matrix, ref int count)
    {
        if (!CanMoveInDirection(x, y, directionX, directionY, hCuts, vCuts, matrix)) return false;
        count++;
        Move(x + directionX, y + directionY, hCuts, vCuts, matrix, ref count++);
        return true;
    }

    public bool CanMoveInDirection(int x, int y, int directionX, int directionY, int[] hCuts, int[] vCuts, int[,] matrix)
    {
        var newX = x + directionX;
        var newY = y + directionY;
        if (newX < 0 
           || newY < 0
           || newX >= matrix.GetLength(0)
           || newY >= matrix.GetLength(1)) return false;

        if (matrix[newX, newY] != 0) return false;

        foreach (var hCut in hCuts) {
            if (CrossesLine(y, newY, hCut)) return false;
        }
        foreach (var vCut in vCuts)
        {
            if (CrossesLine(x, newX, vCut)) return false;
        }
        return true;
    }

    public bool CrossesLine(int previous, int current, int line)
    {
        return (previous < line) != (current < line);
    }
}