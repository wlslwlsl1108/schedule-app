package com.scheduleapp.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    // 인증 하지 않아도 될 URL Path 배열 (인증 제외 경로 -> 화이트리스트)
    private static final String[] WHITE_LIST = {"/", "/signup", "/login"};

    @Override
    public void doFilter(
            ServletRequest servletRequest,       // 서블릿 요청
            ServletResponse servletResponse,     // 서블릿 응답
            FilterChain filterChain              // 다음 필터/서블릿 호출 체인
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;           // HTTP 요청으로 다운캐스팅
        String requestURI = httpRequest.getRequestURI();                                // 요청 URI 추출

        if (!isWhiteList(requestURI)) {                                                 // 화이트리스트 외 요청이면
            HttpSession session = httpRequest.getSession(false);                     // 세션 조회 (없으면 null)

            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute("LOGIN_DIRECTOR") == null) { // 세션 키값 = LOGIN_DIRECTOR  -> login user
                // 세션은 일종의 키/벨류
                throw new RuntimeException("로그인 해주세요.");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);                          // 다음 단계로 전달
    }
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);                   // 경로 매칭
    }
}

/*    [ 다운 캐스팅 ]
         - 상속 관계에서 '상위 타입 참조'를
           실제로 담고 있는 '하위 타입'으로 다시 변경해서 사용하는 것.

         - 명시적 캐스트 필요


      [ 업 캐스팅 ]
         - 자식 -> 부모 변경해서 사용하는 것

         - 자동으로 안전하게 진행됨

*/