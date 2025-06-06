package cat.itacademy.project.business_logic.reward.application;

import cat.itacademy.project.business_logic.reward.domain.Reward;
import cat.itacademy.project.business_logic.reward.domain.RewardRepository;

public class RewardService {
    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public void sendRewardToCustomer(Reward reward) {

        String recipient = reward.getRecipient();
        String description = reward.getDescription();

        EmailSender.send(recipient, description);

        rewardRepository.save(reward);
    }
}
