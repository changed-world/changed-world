package cmc.changedworld.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true,200,"요청에 성공하였습니다."),
    DATABASE_ERROR(false, 400, "데이터베이스 연결에 실패하였습니다."),

    //Empathy 중복 확인
    POST_EMPATHY_INVALID(false, 400, "해당 게시글에 대한 반성해요를 이미 선택하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
