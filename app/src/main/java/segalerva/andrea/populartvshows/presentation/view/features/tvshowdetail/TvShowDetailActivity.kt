package segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail

import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import segalerva.andrea.populartvshows.presentation.view.base.BaseActivity
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment

/**
 * Created by andrea on 19/9/18.
 */
class TvShowDetailActivity : BaseActivity() {

    companion object {

        fun createIntent(activity: BaseActivity, tvShowName: String): Intent {

            val intent = Intent(activity, TvShowDetailActivity::class.java)
            intent.putExtra("tvShowName", tvShowName)
            return intent
        }
    }

    // ------------------------------------------------------------------------------------
    // BaseActivity overrides
    // ------------------------------------------------------------------------------------

    override fun getInitialFragment(): BaseFragment? = TvShowDetailFragment.newInstance()


    // ------------------------------------------------------------------------------------
    // Activity overrides
    // ------------------------------------------------------------------------------------
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> {

                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // ------------------------------------------------------------------------------------
    // Public methods
    // ------------------------------------------------------------------------------------

    fun configureToolbar(toolbar: Toolbar, title: String) {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

}