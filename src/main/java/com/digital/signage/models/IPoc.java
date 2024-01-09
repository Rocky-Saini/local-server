package com.digital.signage.models;

/**
 * @author -Ravi Kumar created on 2/1/2023 3:32 AM
 * @project - Digital Sign-edge
 */
public interface IPoc {
    String getEmail();

    String getFullName();

    String getCountryCode();

    String getMobile();

    class IPocEquals {
        static public boolean isEqual(IPoc iPoc1, IPoc iPoc2) throws IllegalArgumentException {
            if (iPoc1 == null
                    || iPoc2 == null
                    || iPoc1.getEmail() == null
                    || iPoc2.getEmail() == null
                    || iPoc1.getFullName() == null
                    || iPoc2.getFullName() == null
                    || iPoc1.getCountryCode() == null
                    || iPoc2.getCountryCode() == null
                    || iPoc1.getMobile() == null
                    || iPoc2.getMobile() == null
            ) {
                throw new IllegalArgumentException("Either of IPoc is null");
            }
            if (!iPoc1.getEmail().equalsIgnoreCase(iPoc2.getEmail())) {
                return false;
            }
            if (!iPoc1.getFullName().equals(iPoc2.getFullName())) {
                return false;
            }
            if (!iPoc1.getCountryCode().equals(iPoc2.getCountryCode())) {
                return false;
            }
            if (!iPoc1.getMobile().equals(iPoc2.getMobile())) {
                return false;
            }
            return true;
        }
    }
}
