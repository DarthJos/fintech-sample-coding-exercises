package com.github.erikrz.creditapprovalline;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

// SIGUIENTE PASO: VALIDAR DATOS, VALORES NULOS, EXCEPCIONES...
public class CreditApprover {
    private static final int MIN_AGE = 18;
    private static final int MIN_CREDIT_SCORE = 650;
    private static final BigDecimal MAX_DTI_RATIO = new BigDecimal("0.43");

    public Decision getCreditApproval(CustomerProfile customerProfile) {
        var dividendo = customerProfile.getAnnualIncome().divide(BigDecimal.valueOf(12), RoundingMode.HALF_EVEN);
        BigDecimal userDtiRatio = customerProfile.getMonthlyDebt().divide((dividendo), RoundingMode.HALF_EVEN);

        boolean identityPass = customerProfile.getAge() >= MIN_AGE;
        boolean creditScorePass = customerProfile.getCreditScore() >= MIN_CREDIT_SCORE;
        boolean dtiRatioPass = userDtiRatio.compareTo(MAX_DTI_RATIO) <= 0;

        boolean isApproved = identityPass && creditScorePass && dtiRatioPass;

        var rejectionReasonsList = new ArrayList<String>();

        if (!isApproved){
            if (!identityPass) rejectionReasonsList.add("Identity pass: " + identityPass);
            if (!creditScorePass) rejectionReasonsList.add("Credit Score pass: " + creditScorePass);
            if (!dtiRatioPass) rejectionReasonsList.add("DTI Ratio pass: " + dtiRatioPass);
        }

        return new Decision(isApproved, rejectionReasonsList);
    }
}
