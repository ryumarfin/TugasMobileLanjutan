package com.example.latber.fragments


import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.latber.QUOTES_DATA
import com.example.latber.QUOTES_SIGNAL
import com.example.latber.R
import com.example.latber.activities.done
import com.example.latber.data.Quotes
import com.example.latber.jobScheduler.QuotesScheduler
import com.example.latber.sharePreferences.SharePrefHelper
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.et_email
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.io.File

//nama file shared preference
private val PrefFileName1 = "MYFILEPREF01"

class ProfileFragment : Fragment() {



    //inisialisasi var mRewardVid
    private var mRewardVid : RewardedAd?= null
    //    val AdsLength = 3000L
    var mCountDownTimer : CountDownTimer? = null
    var mAdIsLoading = false
//    var mTimerMilliseconds = 0L

    var coin :Int  = 0



//    //definisi JobID
//    val JobId = 18000
//    //membuat recevier untuk menangkap data yang dibroadcast/dikirim dari job scheduler
//    private val QuotesReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            //tangkap data yg dikirimkan
//            val data = intent?.getParcelableExtra<Quotes>(QUOTES_DATA)
//            Log.i("RESULTs",data?.text.toString())
//            //tampilkan data yang ditangkap tadi ke dalam view/xml
//            requireView().quotes.text = data?.text + "\n-" +  data?.author
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fa = File("/data/data/com.example.latber/shared_prefs/"+ PrefFileName1 +".xml")
        //cek apakah file sharedpref exist
        if (fa.exists()) {
            Log.d("TAG_PROFILE", PrefFileName1 + " exist")

            //inisialisasi
            var mySharedHelper = SharePrefHelper(context!!, PrefFileName1)
            //mengambil data dari file sharedpref
            coin = mySharedHelper.Rewardcoin!!

        }
        //jika file tidak ditemukan
        else
            Log.d("TAG_PROFILE", PrefFileName1 + " no exist")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var objView = inflater.inflate(R.layout.fragment_profile, container, false)
        var btnLogout = objView.findViewById<Button>(R.id.btn_logout)
        var btnWatchAds = objView.findViewById<Button>(R.id.btn_watchAds_profile)
        var tv_coin = objView.findViewById<TextView>(R.id.tv_coin_profile)

//        val fa = File("/data/data/com.example.latber/shared_prefs/"+ PrefFileName1 +".xml")
//        //cek apakah file sharedpref exist
//        if (fa.exists()) {
//            Log.d("TAG_PROFILE", PrefFileName1 + " exist")
//
//            //inisialisasi
//            var mySharedHelper = SharePrefHelper(context!!, PrefFileName1)
//            //mengambil data dari file sharedpref
//            var coin = mySharedHelper.coin
            tv_coin.setText(coin.toString())
//
//        }
//        //jika file tidak ditemukan
//        else
//            Log.d("TAG_PROFILE", PrefFileName1 + " no exist")


        btnWatchAds.isEnabled = false

        //Load Rewarded Ads
        loadAd()



        btnLogout.setOnClickListener {
            getActivity()?.finish()
//            activity?.onBackPressed()
        }

        btnWatchAds.setOnClickListener{
            showRewardAds()
        }





//        val intentFilter = IntentFilter(QUOTES_SIGNAL)
//        //mendaftarkan receiver agar ProfileFragment dapat menangkap broadcast
//        requireActivity().registerReceiver(QuotesReceiver,intentFilter)
//        //panggil fungsi startMyJob untuk menjalankan job
//        startMyJob()
        return objView
    }

    override fun onDestroy() {
        super.onDestroy()
        //menghentikan job
//        cancelMyJob()
    }


//    private fun startMyJob() {
//        //definisikan service untuk menjalankan QuoteScheduler pada fragment ini
//        var serviceComponent = ComponentName(requireContext(), QuotesScheduler::class.java)
//        //buat JobInfo dan berikan JobID
//        //berikan informasi tambahan yaitu dengan Network job dapat dijalankan
//        val JobInfo = JobInfo.Builder(JobId, serviceComponent)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//
//        //definisikan job scheduler
//        var JobQoutes = activity?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//        //jalankan job schedulernya
//        JobQoutes.schedule(JobInfo.build())
//    }

//    private fun cancelMyJob() {
//        var JobQoutes = activity?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//        //menghentikan job schedulernya dengan ID JobId
//        JobQoutes.cancel(JobId)
//    }


    //Load Reward Ads
    fun loadAd(){
        var adRequest = AdRequest.Builder().build()

        RewardedAd.load(context, "ca-app-pub-3940256099942544/5224354917",
                adRequest, object : RewardedAdLoadCallback() {
            //ketika iklan gagal di Load
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.d("Rewarded Ads", p0?.message)
                Toast.makeText(context, "Iklan gagal di Load", Toast.LENGTH_SHORT).show()
                mRewardVid = null
                mAdIsLoading = false
                btn_watchAds_profile.isEnabled = true
            }

            //ketika iklan berhasil di Load
            override fun onAdLoaded(p0: RewardedAd) {
                super.onAdLoaded(p0)
                Log.d("Rewarded Ads", "Rewarded Ads berhasil di Load")
                btn_watchAds_profile.isEnabled = true
                mRewardVid = p0
                mAdIsLoading = false
            }
        })
    }


//    //fungsi utk menghitung waktunya agar ada waktu jeda untuk menampilkan Ads
//    private fun createTimer (ms : Long){
//        mCountDownTimer?.cancel()
//        mCountDownTimer = object : CountDownTimer(ms, 50) {
//            override fun onTick(millisUntilFinished: Long) {
////                mTimerMilliseconds = millisUntilFinished
////                timer.text = "seconds remaining: ${ millisUntilFinished / 1000 + 1 }"
//            }
//
//            override fun onFinish() {
//            }
//        }
//    }


    // fungsi utk menampilkan iklan
    private fun showRewardAds() {
        var RewardCoin = coin
        if (mRewardVid != null) {
            mRewardVid?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("Rewarded Ads", "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mRewardVid = null
                    btn_watchAds_profile.isEnabled = false
                    loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("Rewarded Ads", "Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mRewardVid = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("Rewarded Ads", "Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }
            mRewardVid?.show(activity, OnUserEarnedRewardListener() {
                RewardCoin += 100
                coin = RewardCoin
                tv_coin_profile.setText(RewardCoin.toString())
                var mySharedHelper = SharePrefHelper(context!!, PrefFileName1)
                //mengambil data dari file sharedpref
                mySharedHelper.Rewardcoin = coin
            })
        } else {
            Log.d("Rewarded Ads", "Ad wasn't loaded.")

            Toast.makeText(context, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
            reqNewAds()
        }
    }

    private fun reqNewAds() {
        if (!mAdIsLoading && mRewardVid == null) {
            mAdIsLoading = true
            loadAd()
        }
//        resume(AdsLength)
    }

//    private fun resume(milliseconds: Long) {
//        mTimerMilliseconds = milliseconds
//        createTimer(milliseconds)
//        mCountDownTimer?.start()
//    }

    // Resume the game if it's in progress.
    public override fun onResume() {
        super.onResume()
//        resume(mTimerMilliseconds)
//        showInterstitial()
    }

    // Cancel the timer if the game is paused.
    public override fun onPause() {
//        mCountDownTimer?.cancel()
        super.onPause()
    }

}