# ThreadDumpUsageCollector
This jar file will help to generate both the thread dumps and thread usages on any OS if the thread dumps contain cpu time and the elapse time. This will specifically help when capturing dump on Windows OS

Thank you for using the ThreadDumpUsageCollector to capture thread dumps and usages

### FORMAT
```bash
java -jar ThreadDumpUsageCollector-1.0-jar-with-dependencies.jar <JVM_PID> <interval_seconds> <num_dumps> <output_directory>
```

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

"Framework Event Dispatcher: Equinox Container: 3984c5cd-ff73-46d4-91e2-ec9f4f0dc990" #29 daemon prio=5 os_prio=0 cpu=17562.50ms elapsed=105.30s tid=0x000001507f89c3e0 nid=0x2380 runnable  [0x00000033250fa000]
   java.lang.Thread.State: RUNNABLE
	at java.io.WinNTFileSystem.canonicalize0(java.base@17.0.10/Native Method)
	at java.io.WinNTFileSystem.canonicalize(java.base@17.0.10/WinNTFileSystem.java:462)
	at java.io.File.getCanonicalPath(java.base@17.0.10/File.java:626)
	at org.apache.catalina.webresources.AbstractFileResourceSet.file(AbstractFileResourceSet.java:94)
	at org.apache.catalina.webresources.DirResourceSet.getResource(DirResourceSet.java:94)
	at org.apache.catalina.webresources.StandardRoot.getResourceInternal(StandardRoot.java:272)
	at org.apache.catalina.webresources.Cache.getResource(Cache.java:64)
	at org.apache.catalina.webresources.StandardRoot.getResource(StandardRoot.java:211)
	at org.apache.catalina.webresources.StandardRoot.getClassLoaderResource(StandardRoot.java:220)
	at org.apache.catalina.loader.WebappClassLoaderBase.getResourceAsStream(WebappClassLoaderBase.java:1165)
	at org.wso2.carbon.webapp.mgt.loader.CarbonWebappClassLoader.getResourceAsStream(CarbonWebappClassLoader.java:239)
	at org.apache.catalina.startup.ContextConfig.populateJavaClassCache(ContextConfig.java:2497)
	at org.apache.catalina.startup.ContextConfig.populateJavaClassCache(ContextConfig.java:2486)
	at org.apache.catalina.startup.ContextConfig.checkHandlesTypes(ContextConfig.java:2394)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsStream(ContextConfig.java:2338)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsJar(ContextConfig.java:2290)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsUrl(ContextConfig.java:2260)
	at org.apache.catalina.startup.ContextConfig.scanWebXmlFragment(ContextConfig.java:2168)
	at org.apache.catalina.startup.ContextConfig.processAnnotations(ContextConfig.java:2148)
	at org.apache.catalina.startup.ContextConfig.processClasses(ContextConfig.java:1409)
	at org.apache.catalina.startup.ContextConfig.webConfig(ContextConfig.java:1304)
	at org.apache.catalina.startup.ContextConfig.configureStart(ContextConfig.java:987)
	- locked <0x00000000d57d05e8> (a org.wso2.carbon.tomcat.internal.SCIRegistrarContextConfig)
	at org.apache.catalina.startup.ContextConfig.lifecycleEvent(ContextConfig.java:304)
	at org.apache.catalina.util.LifecycleBase.fireLifecycleEvent(LifecycleBase.java:123)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:4852)
	- locked <0x00000000d57cd820> (a org.apache.catalina.core.StandardContext)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	- locked <0x00000000d57cd820> (a org.apache.catalina.core.StandardContext)
	at org.apache.catalina.core.ContainerBase.addChildInternal(ContainerBase.java:683)
	at org.apache.catalina.core.ContainerBase.addChild(ContainerBase.java:658)
	at org.apache.catalina.core.StandardHost.addChild(StandardHost.java:662)
	at org.wso2.carbon.tomcat.internal.CarbonTomcat.addWebApp(CarbonTomcat.java:303)
	at org.wso2.carbon.tomcat.internal.CarbonTomcat.addWebApp(CarbonTomcat.java:209)
	at org.wso2.carbon.webapp.mgt.TomcatGenericWebappsDeployer.handleWebappDeployment(TomcatGenericWebappsDeployer.java:255)
	at org.wso2.carbon.webapp.mgt.TomcatGenericWebappsDeployer.handleExplodedWebappDeployment(TomcatGenericWebappsDeployer.java:243)
	at org.wso2.carbon.webapp.mgt.TomcatGenericWebappsDeployer.handleHotDeployment(TomcatGenericWebappsDeployer.java:173)
	at org.wso2.carbon.webapp.mgt.TomcatGenericWebappsDeployer.deploy(TomcatGenericWebappsDeployer.java:140)
	at org.wso2.carbon.webapp.mgt.AbstractWebappDeployer.deployThisWebApp(AbstractWebappDeployer.java:224)
	at org.wso2.carbon.webapp.mgt.AbstractWebappDeployer.deploy(AbstractWebappDeployer.java:114)
	at org.wso2.carbon.webapp.deployer.WebappDeployer.deploy(WebappDeployer.java:42)
	at org.apache.axis2.deployment.repository.util.DeploymentFileData.deploy(DeploymentFileData.java:136)
	at org.apache.axis2.deployment.DeploymentEngine.doDeploy(DeploymentEngine.java:807)
	- locked <0x00000000c6f2b148> (a org.wso2.carbon.core.CarbonAxisConfigurator)
	at org.apache.axis2.deployment.repository.util.WSInfoList.update(WSInfoList.java:153)
	- locked <0x00000000c6f2b148> (a org.wso2.carbon.core.CarbonAxisConfigurator)
	at org.apache.axis2.deployment.RepositoryListener.update(RepositoryListener.java:377)
	at org.apache.axis2.deployment.RepositoryListener.checkServices(RepositoryListener.java:254)
	at org.apache.synapse.Axis2SynapseController.deployMediatorExtensions(Axis2SynapseController.java:785)
	at org.apache.synapse.Axis2SynapseController.createSynapseEnvironment(Axis2SynapseController.java:403)
	at org.apache.synapse.ServerManager.start(ServerManager.java:192)
	- locked <0x00000000cb04af68> (a org.apache.synapse.ServerManager)
	at org.wso2.carbon.mediation.initializer.ServiceBusInitializer.initESB(ServiceBusInitializer.java:371)
	at org.wso2.carbon.mediation.initializer.ServiceBusInitializer.activate(ServiceBusInitializer.java:170)
	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(java.base@17.0.10/Native Method)
	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(java.base@17.0.10/NativeMethodAccessorImpl.java:77)
	at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(java.base@17.0.10/DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(java.base@17.0.10/Method.java:568)
	at org.eclipse.equinox.internal.ds.model.ServiceComponent.activate(ServiceComponent.java:260)
	at org.eclipse.equinox.internal.ds.model.ServiceComponentProp.activate(ServiceComponentProp.java:146)
	at org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:345)
	at org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
	at org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
	at org.eclipse.equinox.internal.ds.Resolver.getEligible(Resolver.java:343)
	at org.eclipse.equinox.internal.ds.SCRManager.serviceChanged(SCRManager.java:222)
	at org.eclipse.osgi.internal.serviceregistry.FilteredServiceListener.serviceChanged(FilteredServiceListener.java:113)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.dispatchEvent(BundleContextImpl.java:985)
	at org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:234)
	at org.eclipse.osgi.framework.eventmgr.ListenerQueue.dispatchEventSynchronous(ListenerQueue.java:151)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEventPrivileged(ServiceRegistry.java:866)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEvent(ServiceRegistry.java:804)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistrationImpl.register(ServiceRegistrationImpl.java:130)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.registerService(ServiceRegistry.java:228)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.registerService(BundleContextImpl.java:525)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.registerService(BundleContextImpl.java:544)
	at org.wso2.carbon.inbound.endpoint.persistence.service.InboundEndpointPersistenceServiceDSComponent.activate(InboundEndpointPersistenceServiceDSComponent.java:50)
	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(java.base@17.0.10/Native Method)
	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(java.base@17.0.10/NativeMethodAccessorImpl.java:77)
	at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(java.base@17.0.10/DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(java.base@17.0.10/Method.java:568)
	at org.eclipse.equinox.internal.ds.model.ServiceComponent.activate(ServiceComponent.java:260)
	at org.eclipse.equinox.internal.ds.model.ServiceComponentProp.activate(ServiceComponentProp.java:146)
	at org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:345)
	at org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
	at org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
	at org.eclipse.equinox.internal.ds.Resolver.getEligible(Resolver.java:343)
	at org.eclipse.equinox.internal.ds.SCRManager.serviceChanged(SCRManager.java:222)
	at org.eclipse.osgi.internal.serviceregistry.FilteredServiceListener.serviceChanged(FilteredServiceListener.java:113)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.dispatchEvent(BundleContextImpl.java:985)
	at org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:234)
	at org.eclipse.osgi.framework.eventmgr.ListenerQueue.dispatchEventSynchronous(ListenerQueue.java:151)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEventPrivileged(ServiceRegistry.java:866)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEvent(ServiceRegistry.java:804)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistrationImpl.register(ServiceRegistrationImpl.java:130)
	at org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.registerService(ServiceRegistry.java:228)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.registerService(BundleContextImpl.java:525)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.registerService(BundleContextImpl.java:544)
	at org.wso2.carbon.core.init.CarbonServerManager.initializeCarbon(CarbonServerManager.java:529)
	at org.wso2.carbon.core.init.CarbonServerManager.removePendingItem(CarbonServerManager.java:305)
	- locked <0x00000000c54aac78> (a java.lang.Object)
	at org.wso2.carbon.core.init.PreAxis2ConfigItemListener.bundleChanged(PreAxis2ConfigItemListener.java:118)
	- locked <0x00000000c54a7650> (a org.wso2.carbon.core.init.PreAxis2ConfigItemListener)
	at org.eclipse.osgi.internal.framework.BundleContextImpl.dispatchEvent(BundleContextImpl.java:973)
	at org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:234)
	at org.eclipse.osgi.framework.eventmgr.EventManager$EventThread.run(EventManager.java:345)

   Locked ownable synchronizers:
	- None
```
