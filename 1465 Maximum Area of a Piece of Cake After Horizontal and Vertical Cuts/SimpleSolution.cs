/*
You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. 
Since the answer can be a large number, return this modulo 109 + 7.
 */

public class Solution
{
    public int MaxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts)
    {
        int GetMaxGap(int[] cuts, int size)
        {
            Array.Sort(cuts);
            var max = Math.Max(cuts[0], size - cuts[^1]);
            for (var i = 1; i < cuts.Length; i++)
            {
                max = Math.Max(cuts[i] - cuts[i - 1], max);
            }
            return max;
        }

        var maxH = GetMaxGap(horizontalCuts, h);
        var maxW = GetMaxGap(verticalCuts, w);

        return (int)(((long)maxH * (long)maxW) % 1000000007);
    }
}