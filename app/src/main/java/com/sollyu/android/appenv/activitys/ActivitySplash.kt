/*
 * Copyright © 2017 Sollyu <https://www.sollyu.com/>
 *
 * Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * This version of the GNU Lesser General Public License incorporates the terms and conditions of version 3 of the GNU General Public License, supplemented by the additional permissions listed below.
 */

package com.sollyu.android.appenv.activitys

import android.os.Handler
import com.afollestad.materialdialogs.MaterialDialog
import com.sollyu.android.appenv.BuildConfig
import com.sollyu.android.appenv.R
import com.sollyu.android.appenv.commons.Application
import com.umeng.analytics.MobclickAgent


/**
 * 作者：sollyu
 * 时间：2017/11/20
 * 说明：闪屏界面
 */
class ActivitySplash : ActivityBase(), Runnable {

    override fun run() {

        /* Xposed 没有成功的状态 */
        if (!Application.Instance.isXposedWork()) {
            MaterialDialog
                    .Builder(activity)
                    .title(R.string.splash_xposed_not_work_title)
                    .content(R.string.splash_xposed_not_work_content)
                    .positiveText(android.R.string.ok)
                    .onPositive { _, _ -> ActivityMain.launch(activity) }
                    .show()
            return
        }

        /* 状态检查结束、进入主界面 */
        ActivityMain.launch(activity)
    }

    override fun onInitDone() {
        super.onInitDone()

        Handler().postAtTime(this, 1000)
    }

    override fun getMobclickAgentTag(): String {
        return "Splash"
    }

}
