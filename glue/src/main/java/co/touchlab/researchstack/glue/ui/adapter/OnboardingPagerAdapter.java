package co.touchlab.researchstack.glue.ui.adapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.touchlab.researchstack.core.ui.views.LocalWebView;
import co.touchlab.researchstack.glue.R;
import co.touchlab.researchstack.glue.ResearchStack;
import co.touchlab.researchstack.glue.model.StudyOverviewModel;
import co.touchlab.researchstack.glue.ui.views.StudyLandingLayout;
import co.touchlab.researchstack.glue.ui.views.StudyVideoLayout;

public class OnboardingPagerAdapter extends PagerAdapter
{
    private final List<StudyOverviewModel.Question> items;
    private final LayoutInflater                    inflater;

    public OnboardingPagerAdapter(Context context, List<StudyOverviewModel.Question> items)
    {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    /**
     * TODO Clean this code up -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
     * Each case is the same block of code. Figure out what the layout-id needs to be, then upcast
     * to the super class that the layouts each share. If the layouts cant extend from the same parent
     * class, maybe have each implement an interface?
     * <p>
     * interface {
     * public getView()
     * public setFormSteps(Data d)
     * }
     * <p>
     * To discuss.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        StudyOverviewModel.Question item = items.get(position);
        View child;

        if(position == 0)
        {
            //            APCStudyLandingCollectionViewCell *landingCell = (APCStudyLandingCollectionViewCell *)[collectionView dequeueReusableCellWithReuseIdentifier:kAPCStudyLandingCollectionViewCellIdentifier forIndexPath:indexPath];
            //            landingCell.delegate = self;
            //            landingCell.titleLabel.text = studyDetails.caption;
            //            landingCell.subTitleLabel.text = studyDetails.detailText;
            //            landingCell.readConsentButton.hidden = YES;
            //            landingCell.emailConsentButton.hidden = [((APCAppDelegate *)[UIApplication sharedApplication].delegate) hideEmailOnWelcomeScreen];
            //
            //            if ([MFMailComposeViewController canSendMail]) {
            //            [landingCell.emailConsentButton setTitleColor:[UIColor appPrimaryColor] forState:UIControlStateNormal];
            //            [landingCell.emailConsentButton setUserInteractionEnabled:YES];
            //        }else{
            //            [landingCell.emailConsentButton setTitleColor:[UIColor grayColor] forState:UIControlStateNormal];
            //            [landingCell.emailConsentButton setUserInteractionEnabled:NO];
            //        }
            //
            //        if (studyDetails.showsConsent) {
            //            landingCell.readConsentButton.hidden = NO;
            //        }

            StudyLandingLayout layout = (StudyLandingLayout) inflater.inflate(R.layout.item_study_landing,
                    container,
                    false);
            layout.setData(item);

            child = layout;

        }
        else if(! TextUtils.isEmpty(item.getVideoName()))
        {
            StudyVideoLayout layout = (StudyVideoLayout) inflater.inflate(R.layout.item_study_video,
                    container,
                    false);
            layout.setData(item);

            child = layout;


            //            APCStudyVideoCollectionViewCell *videoCell = (APCStudyVideoCollectionViewCell *)[collectionView dequeueReusableCellWithReuseIdentifier:kAPCStudyVideoCollectionViewCellIdentifier forIndexPath:indexPath];
            //            videoCell.delegate = self;
            //            videoCell.titleLabel.text = studyDetails.caption;
            //            videoCell.videoMessageLabel.text = studyDetails.detailText;

        }
        else
        {
            String url = ResearchStack.getInstance().getHTMLFilePath(item.getDetails());
            LocalWebView layout = new LocalWebView(container.getContext());
            layout.loadUrl(url);

            child = layout;

            //            NSString *filePath = [[NSBundle mainBundle] pathForResource: studyDetails.detailText ofType:@"html" inDirectory:@"HTMLContent"];
            //            NSURL *targetURL = [NSURL URLWithString:filePath];
            //            NSURLRequest *request = [NSURLRequest requestWithURL:targetURL];
            //            [webViewCell.webView loadRequest:request];

        }

        container.addView(child);
        return child;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

}
