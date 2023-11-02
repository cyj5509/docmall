package com.docmall.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

// 파일 업로드 관련 필요한 메서드 구성
// 폴더명을 업로드 날짜로 생성
public class FileUtils {

	// 날짜 폴더 생성을 위한 날짜 정보
	public static String getDateFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String str = sdf.format(date); // 예) "2023-11-02"
		
		// File.separator: 각 OS별로 경로 구분자를 반환
		// 유닉스, 맥, 리눅스 구분자: '/' 예) "2023-11-02" -> "2023/11/02"
		// 윈도우즈 구분자: '\' 예) "2023-11-02" -> "2023\11\02"
		return str.replace("-", File.separator);
	}
	
	// 서버에 파일 업로드 기능 구현 및 실제 업로드한 파일명 반환
	public static String uploadFile(String uploadFolder, String dateFolder, MultipartFile uploadFile) {
		
		String realUploadFileName = ""; // 실제 업로드한 파일 이름(파일 이름 중복 방지)
		
		// File: 파일과 폴더 관련 작업하는 기능
		File file = new File(uploadFolder, dateFolder); // 예) "C:/Dev/devtools/upload/" -> "C:/Dev/devtools/upload/2023/11/02"
		
		// 폴더 경로가 없으면 폴더명을 생성함
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		String clientFileName = uploadFile.getOriginalFilename();
		
		// 파일명 중복 방지를 위해 고유한 이름에 사용하는 UUID 사용
		UUID uuid = UUID.randomUUID();
		realUploadFileName = uuid.toString() + "_" + clientFileName;
		
		try {
			// file ─ "C:/Dev/devtools/upload/2023/11/02" + realUploadFileName: 업로드할 파일명
			File saveFile = new File(file, realUploadFileName);
			// 파일 업로드(파일 복사)
			uploadFile.transferTo(saveFile); // 파일 업로드의 핵심 메서드(이전 작업들은 해당 코드를 도출하기 위한 작업)
		} catch (Exception e) {
			e.printStackTrace(); // 파일 업로드 시 예외가 발생되면 예외 관련 정보를 출력
		}
		
		return realUploadFileName; // 상품 테이블에 상품 이미지명으로 저장
	}
}
