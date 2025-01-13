# ThreadDumpUsageCollector
This jar file will help to generate both the thread dumps and thread usages on any OS if the thread dumps contain CPU time and the elapsed time. This will specifically help when capturing dump on Windows OS

Thank you for using the ThreadDumpUsageCollector to capture thread dumps and usages

### FORMAT
```bash
java -jar ThreadDumpUsageCollector-1.0-jar-with-dependencies.jar <JVM_PID> <interval> <num_dumps> <output_directory>
```

###Interval Units
s - Seconds
m - Minutes
h - Hours
d - Days

### EXAMPLE
```bash
java -jar ThreadDumpUsageCollector-1.0-jar-with-dependencies.jar 1234 2s 10 DUMPS_DIRECTORY
```
### CORRELATING DUMPS AND USAGES
When correlating thread dumps and usages, you can simply use the Thread ID value in the thread usage and that will match with the tid value of the thread in the thread dump. Also you can refer to the Thread Name value in the thread usage file to verify the thread name in the thread dump.

### SAMPLE THREAD USAGE
```
   Thread ID          CPU Usage(%)          Thread Name
0x000001505c8cae60          3.19          main
0x0000015079af9d00          0.00          Reference Handler
0x0000015079afaa90          0.00          Finalizer
0x000001507e001090          0.00          Signal Dispatcher
0x000001507e0046f0          0.00          Attach Listener
0x000001507e004fc0          0.00          Service Thread
0x000001507e005890          0.00          Monitor Deflation Thread
0x000001507e007550          22.78          C2 CompilerThread0
0x000001507e007f10          4.87          C1 CompilerThread0
0x000001507e010830          0.24          Sweeper thread
0x0000015079ae4730          0.00          Common-Cleaner
0x000001507e255dc0          0.00          Notification Thread
0x000001507e63d580          0.00          RMI TCP Accept-0
0x000001507e446280          0.00          Log4j2-TF-1-AsyncLogger[AsyncContext@5e2de80c]-1
0x000001507f89b9c0          0.43          Active Thread: Equinox Container: 3984c5cd-ff73-46d4-91e2-ec9f4f0dc990
0x000001507f89c3e0          13.89          Framework Event Dispatcher: Equinox Container: 3984c5cd-ff73-46d4-91e2-ec9f4f0dc990
```

### SAMPLE THREAD DUMP
```
"RMI TCP Accept-0" #17 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=110.28s tid=0x000001507e63d580 nid=0x1c9c runnable  [0x00000033243fe000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.Net.accept(java.base@17.0.10/Native Method)
	at sun.nio.ch.NioSocketImpl.accept(java.base@17.0.10/NioSocketImpl.java:760)
	at java.net.ServerSocket.implAccept(java.base@17.0.10/ServerSocket.java:675)
	at java.net.ServerSocket.platformImplAccept(java.base@17.0.10/ServerSocket.java:641)
	at java.net.ServerSocket.implAccept(java.base@17.0.10/ServerSocket.java:617)
	at java.net.ServerSocket.implAccept(java.base@17.0.10/ServerSocket.java:574)
	at java.net.ServerSocket.accept(java.base@17.0.10/ServerSocket.java:532)
	at sun.management.jmxremote.LocalRMIServerSocketFactory$1.accept(jdk.management.agent@17.0.10/LocalRMIServerSocketFactory.java:52)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(java.rmi@17.0.10/TCPTransport.java:413)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(java.rmi@17.0.10/TCPTransport.java:377)
	at java.lang.Thread.run(java.base@17.0.10/Thread.java:840)

   Locked ownable synchronizers:
	- <0x00000000c05c1f80> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)

"Log4j2-TF-1-AsyncLogger[AsyncContext@5e2de80c]-1" #20 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=110.07s tid=0x000001507e446280 nid=0x2d1c runnable  [0x0000003324ffe000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at jdk.internal.misc.Unsafe.park(java.base@17.0.10/Native Method)
	- parking to wait for  <0x00000000c06470a8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(java.base@17.0.10/LockSupport.java:252)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@17.0.10/AbstractQueuedSynchronizer.java:1672)
	at com.lmax.disruptor.TimeoutBlockingWaitStrategy.waitFor(TimeoutBlockingWaitStrategy.java:38)
	at com.lmax.disruptor.ProcessingSequenceBarrier.waitFor(ProcessingSequenceBarrier.java:56)
	at com.lmax.disruptor.BatchEventProcessor.processEvents(BatchEventProcessor.java:159)
	at com.lmax.disruptor.BatchEventProcessor.run(BatchEventProcessor.java:125)
	at java.lang.Thread.run(java.base@17.0.10/Thread.java:840)

   Locked ownable synchronizers:
	- None

"Active Thread: Equinox Container: 3984c5cd-ff73-46d4-91e2-ec9f4f0dc990" #27 prio=5 os_prio=0 cpu=437.50ms elapsed=105.70s tid=0x000001507f89b9c0 nid=0x2a5c waiting on condition  [0x00000033258fe000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at jdk.internal.misc.Unsafe.park(java.base@17.0.10/Native Method)
	- parking to wait for  <0x00000000c0647910> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(java.base@17.0.10/LockSupport.java:252)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@17.0.10/AbstractQueuedSynchronizer.java:1672)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@17.0.10/ScheduledThreadPoolExecutor.java:1182)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@17.0.10/ScheduledThreadPoolExecutor.java:899)
	at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@17.0.10/ThreadPoolExecutor.java:1062)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@17.0.10/ThreadPoolExecutor.java:1122)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@17.0.10/ThreadPoolExecutor.java:635)
	at java.lang.Thread.run(java.base@17.0.10/Thread.java:840)

   Locked ownable synchronizers:
	- None
```
