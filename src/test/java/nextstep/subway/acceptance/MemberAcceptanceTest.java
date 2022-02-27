package nextstep.subway.acceptance;

import static nextstep.subway.acceptance.MemberSteps.회원_삭제_요청;
import static nextstep.subway.acceptance.MemberSteps.회원_삭제_확인;
import static nextstep.subway.acceptance.MemberSteps.회원_생성_요청;
import static nextstep.subway.acceptance.MemberSteps.회원_생성_확인;
import static nextstep.subway.acceptance.MemberSteps.회원_정보_수정_요청;
import static nextstep.subway.acceptance.MemberSteps.회원_정보_수정_확인;
import static nextstep.subway.acceptance.MemberSteps.회원_정보_조회_요청;
import static nextstep.subway.acceptance.MemberSteps.회원_정보_조회됨;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberAcceptanceTest extends AcceptanceTest {
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "password";
    public static final int AGE = 20;

    /**
     * Scenario : 회원 정보를 관리
     *  When 회원 생성을 요청
     *  Then 회원 생성됨
     *  When 회원 정보 조회 요청
     *  Then 회원 정보 조회됨
     *  When 회원 정보 수정 요청
     *  Then 회원 정보 수정됨
     *  When 회원 삭제 요청
     *  Then 회원 삭제됨
     */
    @DisplayName("회원 정보를 관리한다.")
    @Test
    void manageMember() {
        ExtractableResponse<Response> createResponse = 회원_생성_요청(EMAIL, PASSWORD, AGE);
        회원_생성_확인(createResponse);

        ExtractableResponse<Response> findResponse = 회원_정보_조회_요청(createResponse);
        회원_정보_조회됨(findResponse, EMAIL, AGE);

        ExtractableResponse<Response> updateResponse = 회원_정보_수정_요청(createResponse, "new" + EMAIL, "new" + PASSWORD, AGE + 10);
        회원_정보_수정_확인(updateResponse, "new" + EMAIL, "new" + PASSWORD, AGE + 10);

        ExtractableResponse<Response> deleteResponse = 회원_삭제_요청(createResponse);
        회원_삭제_확인(deleteResponse, createResponse);

    }
}