#!/bin/bash
# jvm参数设置
#-Duser.timezone=Asia/Shanghai
#-Dclient.encoding.override=UTF-8
#-Dfile.encoding=UTF-8
#-Djava.security.egd=file:/dev/./urandom
#
#-XX:+UnlockExperimentalVMOptions
#-XX:+UseCGroupMemoryLimitForHeap
#-XX:MaxRAMFraction=1
#(-Xms1024m -Xmx1024m -Xmn400m -Xss1M -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M)
#-XX:SurvivorRatio=8
#-XX:MaxTenuringThreshold=5
#-XX:PretenureSizeThreshold=1M
#(-XX:SoftRefLRUPolicyMSPerMB=0)
#(-XX:-OmitStackTraceInFastThrow)
#
#(-XX:ParallelGCThreads=4)
#(-XX:+DisableExplicitGC)
#-XX:+ExplicitGCInvokesConcurrent
#-XX:+ScavengeBeforeFullGC
#-XX:+UseParNewGC
#-XX:+UseConcMarkSweepGC
#-XX:+UseCMSCompactAtFullCollection
#-XX:CMSFullGCsBeforeCompaction=9
#(-XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=92)
#-XX:+CMSScavengeBeforeRemark
#-XX:+CMSParallellnitialMarkEnabled
#-XX:+CMSParallelRemarkEnabled
#
#(-XX:+CMSClassUnloadingEnabled)
#
#-XX:+HeapDumpOnOutOfMemoryError
#-XX:HeapDumpPath=/var/log/HeapDumpOnOutOfMemoryError/
#-XX:+PrintGCDetails
#-XX:+PrintGCDateStamps
#-XX:+PrintGCTimeStamps
#-XX:+PrintHeapAtGC
#-XX:+PrintTenuringDistribution
#-XX:+PrintGCApplicationConcurrentTime
#-XX:+PrintGCApplicationStoppedTime
#-XX:+UseGCLogFileRotation
#-Xloggc:/var/log/gc.log
#-XX:NumberOfGCLogFiles=5
#-XX:GCLogFileSize=5M

JAVA_OPT="-Xms1024m -Xmx1024m -Xmn400m -Xss1M -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -Duser.timezone=Asia/Shanghai -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -XX:+UnlockExperimentalVMOptions -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5 -XX:PretenureSizeThreshold=1M -XX:+ExplicitGCInvokesConcurrent -XX:+ScavengeBeforeFullGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=9 -XX:+CMSScavengeBeforeRemark -XX:+CMSParallellnitialMarkEnabled -XX:+CMSParallelRemarkEnabled -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/HeapDumpOnOutOfMemoryError/ -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -XX:+UseGCLogFileRotation -Xloggc:/var/log/gc.log -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=5M"
APP_NAME={project.name}-{project.version}.jar

start_app(){
	echo $JAVA_OPT

	cd `dirname "$0"`

	java ${JAVA_OPT} -jar app/${APP_NAME} --spring.config.location=cfg/ --logging.path=logs/ --server.tomcat.basedir=tmp/
}

stop_app(){
   PID=$(ps -ef | grep "${APP_NAME}" | grep -v grep | awk '{ print $2 }')
   echo "start kill ${APP_NAME} ${PID}"
   kill -9 $PID
}


case $1 in
	start)
		start_app
		;;
	stop)
		stop_app
		;;
	restart)
		stop_app
		start_app
		;;
	*)
		start_app
		;;
esac
