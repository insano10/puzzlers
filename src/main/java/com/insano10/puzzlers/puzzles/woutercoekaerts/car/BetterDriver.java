package com.insano10.puzzlers.puzzles.woutercoekaerts.car;

import com.insano10.puzzlers.puzzles.woutercoekaerts.PuzzlerRunner;

public class BetterDriver implements Runnable
{
    @Override
    public void run()
    {
        final BetterCar car = new BetterCar();

        Accelerator acc = new Accelerator(car);
        Vroomer vroomer = new Vroomer(car);
        AcceleratorStopper stopper = new AcceleratorStopper(acc, car);

        acc.start();
        vroomer.start();
        stopper.start();

        try
        {
            System.out.println("going to sleep");
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private static class Accelerator extends Thread
    {
        public Accelerator(BetterCar car)
        {
            super(() -> {
                while (true)
                {
                    car.accelerate(1001);
                }
            });
        }
    }

    private static class Vroomer extends Thread
    {
        public Vroomer(BetterCar car)
        {
            super(() -> {
                while (true)
                {
                    car.vroom();
                }
            });
        }
    }

    private static class AcceleratorStopper extends Thread
    {
        public AcceleratorStopper(Accelerator acc, BetterCar car)
        {
            super(() -> {
                while (true)
                {
                    if (acc.getState() == State.RUNNABLE)
                    {
//                        System.out.println("stopping");
                        acc.suspend();
                        car.crash();
                        //deadlock?
                    }
                    else
                    {
//                        System.out.println("resuming");
                        acc.resume();
                    }
                }
            });
        }
    }

    public static void main(String args[])
    {
        PuzzlerRunner.run(new BetterDriver());
    }
}
