package com.insano10.puzzlers.puzzles.woutercoekaerts.car;

import com.insano10.puzzlers.puzzles.woutercoekaerts.PuzzlerRunner;

public class Driver implements Runnable
{
    @Override
    public void run()
    {
        // TODO break the speed limit
        Car car = new Car();
        car.accelerate(Integer.MIN_VALUE+1001);
        car.accelerate(Integer.MIN_VALUE);
        car.vroom();
    }

    public static void main(String args[])
    {
        PuzzlerRunner.run(new Driver());
    }
}
