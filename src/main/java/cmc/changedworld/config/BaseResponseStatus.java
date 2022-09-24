package cmc.changedworld.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true,200,"요청에 성공하였습니다."),


    /**
     * 게시물 서버 오류
     */
    FAILED_TO_GET_POST_LIST_IN_SERVER(false,4001,"게시물 조회 서버 오류"),
    FAILED_TO_CREAT_POST_IN_SERVER(false,4002,"게시물 생성 서버 오류"),
    FAILED_TO_GET_POST_IN_SERVER(false, 4003, "게시물 조회 서버 오류");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
