package com.example.demo.teammatchBean;



/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 20:16 2018-04-24
 */
public class TeamJsonString {
    public String reason;
    public TeamResult teamResult;
    public String error_code;

    public TeamJsonString(String reason, TeamResult teamResult, String error_code) {
        this.reason = reason;
        this.teamResult = teamResult;
        this.error_code = error_code;
    }

    public TeamJsonString() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TeamResult getTeamResult() {
        return teamResult;
    }

    public void setTeamResult(TeamResult teamResult) {
        this.teamResult = teamResult;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}


