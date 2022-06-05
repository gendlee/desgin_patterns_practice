package facadepattern.facade;

import facadepattern.subsystem.CPU;
import facadepattern.subsystem.Disk;
import facadepattern.subsystem.Memory;

public class Computer {
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {
        System.out.println("computer is starting...");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("computer started!");
    }

    public void shutdown() {
        System.out.println("computer is shutting down...");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer has shut down!");
    }
}
