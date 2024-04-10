package com.cherry.lib.doc.dialog

import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.XPopupCallback

/**
 * @auther Jasper Jiao
 * @time 2022/6/21 11:18
 * @des XPopupCallback的实现类
 */
open class XPopupCallbackImp : XPopupCallback {
    override fun onCreated(popupView: BasePopupView?) {
    }

    override fun beforeShow(popupView: BasePopupView?) {
    }

    override fun onShow(popupView: BasePopupView?) {
    }

    override fun onDismiss(popupView: BasePopupView?) {
    }

    override fun beforeDismiss(popupView: BasePopupView?) {
    }

    override fun onBackPressed(popupView: BasePopupView?): Boolean {
        return true
    }

    override fun onKeyBoardStateChanged(popupView: BasePopupView?, height: Int) {
    }

    override fun onDrag(popupView: BasePopupView?, value: Int, percent: Float, upOrLeft: Boolean) {
    }

    override fun onClickOutside(popupView: BasePopupView?) {
    }
}