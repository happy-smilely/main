package com.happy.smilely.main_project.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PushScheduler {
	
	//특정 시간에 결과 알림 일괄 전송
	@Scheduled(cron = "${push.cron}")
	public void batchReportPush() {
		
		StopWatch stopWatch = new StopWatch("Batch-Push");
		
		log.info("### 알림 스케쥴러 실행");
		stopWatch.start();
		//reportPushService.batchReportPush();
		stopWatch.stop();
		log.info("### 알림 스케쥴러 종료 - 소요 시간 {}", stopWatch.shortSummary());

	}
	
}
