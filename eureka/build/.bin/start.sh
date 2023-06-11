#!/bin/bash
CONFIG_FILE=/opt/app/app.conf

# Xms 最小; Xmx 最大; Xmn 年轻态堆大小; MetaspaceSize 元数据区大小; Xss 置每个线程的堆栈大小;
MIN_OPT="-Xms384m -Xmx384m -Xmn144m -XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=384m -Xss512K"
MIDDLE_OPT="-Xms512m -Xmx512m -Xmn200m -XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=512m -Xss512K"
MAX_OPT="-Xms1024m -Xmx1024m -Xmn400m -XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=1024m -Xss512K"

JAVA_OPT=""

APP_NAME={project.name}-{project.version}.jar

start_app(){
	JAVA_OPT="${MAX_OPT}"

	JAVA_OPT="$JAVA_OPT -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:+CMSClassUnloadingEnabled  -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=5M"

	echo $JAVA_OPT

	cd `dirname "$0"`

	nohup java ${JAVA_OPT} -jar app/${APP_NAME} --spring.config.location=cfg/ --logging.path=logs/ --server.tomcat.basedir=tmp/ > /dev/null 2>&1 &
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
