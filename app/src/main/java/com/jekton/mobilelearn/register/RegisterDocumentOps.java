package com.jekton.mobilelearn.register;

import com.jekton.mobilelearn.common.dv.BasicDocumentOps;

/**
 * @author Jekton
 */
interface RegisterDocumentOps extends BasicDocumentOps<RegisterViewOps> {

    void onRegister(String name, String email, String password);

}
