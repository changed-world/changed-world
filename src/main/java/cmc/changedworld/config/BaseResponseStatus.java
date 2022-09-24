package cmc.changedworld.config;

import cmc.changedworld.domain.Empathy;
import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true,200,"요청에 성공하였습니다."),
    DATABASE_ERROR(false, 400, "데이터베이스 연결에 실패하였습니다."),

    /**
     * 사용자 관련 오류
     */
    USER_ID_NOT_FOUND(false, 1001, "사용자 ID를 찾을 수 없습니다."),

    /**
     * 투표 관련 오류
     */
    VOTE_NOT_OPENED(false, 2002, "진행중인 투표가 없습니다."),

    /**
     * 게시물 서버 오류
     */
    FAILED_TO_GET_POST_LIST_IN_SERVER(false,4001,"게시물 조회 서버 오류"),
    FAILED_TO_CREAT_POST_IN_SERVER(false,4002,"게시물 생성 서버 오류"),
    FAILED_TO_GET_COMMENT_LIST_IN_SERVER(false, 4003, "댓글 리스트 조회 서버 오류"),

    /**
     * Empathy 서버 오류
     */
    POST_EMPATHY_DUPLICATION(false, 5001, "해당 게시글에 대한 반성해요를 이미 선택하였습니다."),
    POST_EMPATHY_INVALID_GENERATION(false, 5001, "해당 게시글에 반성해요를 남길 수 없는 세대입니다."),


    /**
     * Look 서버 오류
     */
    POST_LOOK_INVALID(false, 6001, "해당 게시글에 대한 중복 조회입니다."),

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
