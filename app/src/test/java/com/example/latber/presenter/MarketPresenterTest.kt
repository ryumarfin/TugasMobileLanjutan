package com.example.latber.presenter

import com.example.latber.Interface.MarketInterface
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class MarketPresenterTest {
    //duplikat operasi dlm MarketInterface
    private var view: MarketInterface = mock(MarketInterface::class.java)

    //kirim view ke presenter
    private var presenter = MarketPresenter(view)

    @Test
    fun itemList() {
//        var
    }
}