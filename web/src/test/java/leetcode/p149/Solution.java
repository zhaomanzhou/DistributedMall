package leetcode.p149;

class Solution {
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }

        int l = points.length;
        int res = 2;
        //loop
        for (int i = 0; i < l - res; i++)
        {
            int[] a = points[i];
            int same = 1;
            for (int j = i + 1; j < l - 1; j++)
            {
                int[] b = points[j];
                int cnt = 1;
                if (a[0] == b[0] && a[1] == b[1])
                {
                    ++same;
                } else
                {
                    for (int k = j + 1; k < l; k++)
                    {
                        int[] c = points[k];
                        if ((long) (a[1] - b[1]) * (c[0] - a[0]) == (long) (a[0] - b[0]) * (c[1] - a[1])) {
                            ++cnt;
                        }
                    }
                }

                res = Math.max(res, cnt + same);
            }
        }

        return res;
    }
}