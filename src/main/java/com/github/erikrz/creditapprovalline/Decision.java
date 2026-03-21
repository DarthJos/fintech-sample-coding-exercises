package com.github.erikrz.creditapprovalline;

import java.util.List;

public class Decision {
    private boolean isApproved;
    private List<String> rejectionReasons;

    public Decision(boolean isApproved, List<String> rejectionReasons) {
        this.isApproved = isApproved;
        this.rejectionReasons = rejectionReasons;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public List<String> getRejectionReasons() {
        return rejectionReasons;
    }

    public void setRejectionReasons(List<String> rejectionReasons) {
        this.rejectionReasons = rejectionReasons;
    }
}
