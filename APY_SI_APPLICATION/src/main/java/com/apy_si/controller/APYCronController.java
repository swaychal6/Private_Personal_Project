package com.apy_si.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apy_si.CBSUtil.MainFlowAPY;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/APY")
public class APYCronController {

	private static final Logger logger = LogManager.getLogger(APYCronController.class);

	private final MainFlowAPY mainFlowAPY;

	@PostMapping("/SI/95")
	@Scheduled(cron = "0 02 04 * * *")
	public void execute_95_JRGB() {
		mainFlowAPY.completableFuture("M", "95");
		mainFlowAPY.completableFuture("Q", "95");
		mainFlowAPY.completableFuture("H", "95");
	}

	@PostMapping("/SI/27")
	@Scheduled(cron = "0 02 06 * * *")
	public void execute_27() {
		mainFlowAPY.completableFuture("M", "27");
		mainFlowAPY.completableFuture("Q", "27");
		mainFlowAPY.completableFuture("H", "27");
	}

	@PostMapping("/SI/1859")
	@Scheduled(cron = "0 02 01 * * *")
	public void execute_1859() {
		mainFlowAPY.completableFuture("M", "1859");
		mainFlowAPY.completableFuture("Q", "1859");
		mainFlowAPY.completableFuture("H", "1859");
	}

	@PostMapping("/SI/102")
	@Scheduled(cron = "0 50 05 * * *")
	public void execute_102() {
		mainFlowAPY.completableFuture("M", "102");
		mainFlowAPY.completableFuture("Q", "102");
		mainFlowAPY.completableFuture("H", "102");
	}

	@PostMapping("/SI/1653")
	@Scheduled(cron = "0 00 06 * * *")
	public void execute_1653() {
		mainFlowAPY.completableFuture("M", "1653");
		mainFlowAPY.completableFuture("Q", "1653");
		mainFlowAPY.completableFuture("H", "1653");
	}

	@PostMapping("/SI/1645")
	@Scheduled(cron = "0 02 05 * * *")
	public void execute_1645() {
		mainFlowAPY.completableFuture("M", "1645");
		mainFlowAPY.completableFuture("Q", "1645");
		mainFlowAPY.completableFuture("H", "1645");
	}

	@PostMapping("/SI/1664")
	@Scheduled(cron = "0 15 06 * * *")
	public void execute_1664() {
		mainFlowAPY.completableFuture("M", "1664");
		mainFlowAPY.completableFuture("Q", "1664");
		mainFlowAPY.completableFuture("H", "1664");
	}

	@PostMapping("/SI/251")
	@Scheduled(cron = "0 02 08 * * *")
	public void execute_251() {
		mainFlowAPY.completableFuture("M", "251");
		mainFlowAPY.completableFuture("Q", "251");
		mainFlowAPY.completableFuture("H", "251");
	}

	@PostMapping("/SI/108")
	@Scheduled(cron = "0 15 05 * * *")
	public void execute_108() {
		mainFlowAPY.completableFuture("M", "108");
		mainFlowAPY.completableFuture("Q", "108");
		mainFlowAPY.completableFuture("H", "108");
	}

	@PostMapping("/SI/59")
	@Scheduled(cron = "0 02 08 * * *")
	public void execute_59() {
		mainFlowAPY.completableFuture("M", "59");
		mainFlowAPY.completableFuture("Q", "59");
		mainFlowAPY.completableFuture("H", "59");
	}

	@PostMapping("/SI/61")
	@Scheduled(cron = "0 05 08 * * *")
	public void execute_61() {
		mainFlowAPY.completableFuture("M", "61");
		mainFlowAPY.completableFuture("Q", "61");
		mainFlowAPY.completableFuture("H", "61");
	}

	@PostMapping("/SI/62")
	@Scheduled(cron = "0 10 08 * * *")
	public void execute_62() {
		mainFlowAPY.completableFuture("M", "62");
		mainFlowAPY.completableFuture("Q", "62");
		mainFlowAPY.completableFuture("H", "62");
	}

	@PostMapping("/SI/96")
	@Scheduled(cron = "0 45 05 * * *")
	public void execute_96() {
		mainFlowAPY.completableFuture("M", "96");
		mainFlowAPY.completableFuture("Q", "96");
		mainFlowAPY.completableFuture("H", "96");
	}

	@PostMapping("/SI/99")
	@Scheduled(cron = "0 30 06 * * *")
	public void execute_99() {
		mainFlowAPY.completableFuture("M", "99");
		mainFlowAPY.completableFuture("Q", "99");
		mainFlowAPY.completableFuture("H", "99");
	}

	@PostMapping("/SI/101")
	@Scheduled(cron = "0 10 06 * * *")
	public void execute_101() {
		mainFlowAPY.completableFuture("M", "101");
		mainFlowAPY.completableFuture("Q", "101");
		mainFlowAPY.completableFuture("H", "101");
	}

	@PostMapping("/SI/374")
	@Scheduled(cron = "0 30 08 * * *")
	public void execute_374() {
		mainFlowAPY.completableFuture("M", "374");
		mainFlowAPY.completableFuture("Q", "374");
		mainFlowAPY.completableFuture("H", "374");
	}

	@PostMapping("/SI/1833")
	@Scheduled(cron = "0 02 07 * * *")
	public void execute_1833() {
		mainFlowAPY.completableFuture("M", "1833");
		mainFlowAPY.completableFuture("Q", "1833");
		mainFlowAPY.completableFuture("H", "1833");
	}

	@PostMapping("/SI/2213")
	@Scheduled(cron = "0 20 05 * * *")
	public void execute_2213() {
		mainFlowAPY.completableFuture("M", "2213");
		mainFlowAPY.completableFuture("Q", "2213");
		mainFlowAPY.completableFuture("H", "2213");
	}

	@PostMapping("/")
	@Scheduled(cron = "0 50 18 * * *")
	public void executeStart() {
		logger.info("Application scheduler is Enabled and Started...");
	}

}
