import numpy as np
from scipy.stats import ttest_1samp, wilcoxon, ttest_ind, mannwhitneyu

harm_pd = pd.read_csv("data/harmfulness.csv")
cls_before = np.array([10, 5.5, 6.625, 5.5, 7.75, 7.75, 7.75, 5.5, 8.875, 5.5, 8.875, 5.5, 8.875, 3.25, 8.875, 3.25, 10.0, 8.875, 6.625, 6.625, 7.75, 8.875, 3.25, 10, 8.875, 6.625, 10, 8.875])
cls_after = np.array([8.875,0,0,0,0,0,0,0,0,0,8.875 ,0 ,0 ,0 ,0 ,0 ,0 ,6.625 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0,0])
z_statistic, p_value = wilcoxon(cls_after - cls_before)
print("cls before :", cls_before)
print("cls after :", cls_after)
print("p-value :", p_value)
# 7e-02, 0.07
# 5.253976950296128e-06, 0.525e-06
# 5.7e-02, 0.057