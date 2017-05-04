package com.pathfinding;

public final class PathAdapter
{
    private static volatile com.pathfinding.PathAdapter instance;

    private PathAdapter()
    {

    }

    public static com.pathfinding.PathAdapter getInstance()
    {
        if (instance == null)
        {
            synchronized (PathAdapter.class)
            {
                if (instance == null)
                {
                    instance = new PathAdapter();
                }
            }
        }
        return instance;
    }

    public static byte[] toByteArray(byte[] initArray, int graphWidth, int graphHeight, Path path, byte pathValue)
    {
        byte[] result = initArray.clone();
        if (path != null)
        {
            try
            {
                while (!path.isEmpty())
                {
                    Step step = path.popStep();
                    int index = step.getY() * graphHeight + step.getX();
                    if (isOutOfBounds(step.getX(), step.getY(), graphWidth, graphHeight))
                    {
                        throw new ArrayIndexOutOfBoundsException("[Error:toByteArray]Index: " + index + " occurs outside the bounds of the graph.");
                    }
                    result[index] = pathValue;
                }
            }
            catch (Exception e)
            {
                result = initArray.clone();
            }

        }
        return result;
    }

    public static void printByteArray(byte[] graph, int graphWidth, int graphHeight, java.io.PrintStream out)
    {
        try
        {
            for (int j = 0; j < graphHeight; ++j)
            {
                for (int i = 0; i < graphWidth; ++i)
                {
                    int index = j * graphHeight + i;
                    if (isOutOfBounds(i, j, graphWidth, graphHeight))
                    {
                        throw new ArrayIndexOutOfBoundsException("[Error:PrintByteArray]Index: " + index + " occurs outside the bounds of the graph.");
                    }
                    out.print(graph[index] + " ");
                }
                out.println();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(out);
        }
    }

    private static boolean isOutOfBounds(int x, int y, int width, int height)
    {
        return ((x < 0 || x >= width) || (y < 0 || y >= height));
    }
}
